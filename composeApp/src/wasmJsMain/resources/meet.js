 // Mock participantes (poderia vir de backend ou WS)
  const participants = ["Usuário 1", "Usuário 2", "Usuário 3", "Usuário 4", "Usuário 5", "Usuário 6"];

  const participantsColumn = document.getElementById("participants-column");

  // Função para renderizar os participantes em linhas de 2
  function renderParticipants() {
    participantsColumn.innerHTML = "";

    for (let i = 0; i < participants.length; i += 2) {
      const row = document.createElement("div");
      row.className = "participant-row";

      const p1 = document.createElement("div");
      p1.className = "participant-box";
      p1.textContent = participants[i];
      row.appendChild(p1);

      if (i + 1 < participants.length) {
        const p2 = document.createElement("div");
        p2.className = "participant-box";
        p2.textContent = participants[i + 1];
        row.appendChild(p2);
      } else {
        // Se for ímpar, adiciona spacer para alinhar
        row.classList.add("single");
        const spacer = document.createElement("div");
        spacer.className = "participant-box spacer";
        row.appendChild(spacer);
      }

      participantsColumn.appendChild(row);
    }
  }

  renderParticipants();

  // Variáveis para WebRTC e WebSocket
  let localStream = null;
  let peers = {};
  let ws = null;
  let myId = Math.random().toString(36).substr(2, 9);
  let room = null;

  const localVideo = document.getElementById("localVideo");

  // Função para capturar áudio e vídeo local
  async function startLocalStream() {
    try {
      localStream = await navigator.mediaDevices.getUserMedia({ video: true, audio: true });
      localVideo.srcObject = localStream;
    } catch (err) {
      alert("Não foi possível acessar a câmera e o microfone.");
      throw err;
    }
  }

  // Função para enviar mensagem pelo WebSocket
  function send(msg) {
    if (ws && ws.readyState === WebSocket.OPEN) {
      ws.send(JSON.stringify(msg));
    }
  }

  // Função para criar peer connection e setar eventos (simplificado)
  function createPeer(peerId, isInitiator) {
    const pc = new RTCPeerConnection();

    // Adiciona tracks locais
    localStream.getTracks().forEach(track => pc.addTrack(track, localStream));

    pc.onicecandidate = event => {
      if (event.candidate) {
        send({
          type: "signal",
          to: peerId,
          from: myId,
          sala: room,
          payload: {
            type: "candidate",
            candidate: event.candidate,
          }
        });
      }
    };

    // Receber stream remoto
    pc.ontrack = event => {
      addRemoteVideo(peerId, event.streams[0]);
    };

    if (isInitiator) {
      pc.createOffer().then(offer => {
        pc.setLocalDescription(offer);
        send({
          type: "signal",
          to: peerId,
          from: myId,
          sala: room,
          payload: offer
        });
      });
    }

    peers[peerId] = pc;
    return pc;
  }

  // Adicionar vídeo remoto no DOM
  function addRemoteVideo(peerId, stream) {
    let video = document.getElementById("remoteVideo-" + peerId);
    if (!video) {
      video = document.createElement("video");
      video.id = "remoteVideo-" + peerId;
      video.autoplay = true;
      video.playsInline = true;
      video.style.width = "100%";
      video.style.height = "100%";
      video.style.borderRadius = "12px";

      // Criar container para o vídeo
      const videoArea = document.getElementById("video-area");
      videoArea.appendChild(video);
    }
    video.srcObject = stream;
  }

  // Remover vídeo remoto
  function removeRemoteVideo(peerId) {
    const video = document.getElementById("remoteVideo-" + peerId);
    if (video) {
      video.srcObject = null;
      video.remove();
    }
  }

  // Função que inicia a transmissão (captura + WebSocket + signaling)
  async function startTransmission() {
    room = "sala-de-aula"; // pode ser obtido via input

    try {
      await startLocalStream();
    } catch {
      return; // aborta se falhar no getUserMedia
    }

    ws = new WebSocket(`ws://${location.host}/signal`);

    ws.onerror = event => {
      alert("Erro na conexão WebSocket. Verifique o servidor.");
      console.error("WebSocket error:", event);
    };

    ws.onopen = () => {
      console.log("WebSocket conectado");
      send({ type: "join", id: myId, sala: room });
    };

    ws.onclose = () => {
      alert("Conexão WebSocket fechada.");
      console.warn("WebSocket fechado");
    };

    ws.onmessage = async (event) => {
      try {
        const msg = JSON.parse(event.data);

        if (msg.type === "peers") {
          msg.peers.forEach(peerId => {
            createPeer(peerId, true);
          });
        } else if (msg.type === "new-peer") {
          const peerId = msg.id;
          createPeer(peerId, false);
        } else if (msg.type === "signal") {
          const peerId = msg.from;
          let pc = peers[peerId];
          if (!pc) {
            pc = createPeer(peerId, false);
          }
          const payload = msg.payload;

          if (payload.type === "offer") {
            await pc.setRemoteDescription(new RTCSessionDescription(payload));
            const answer = await pc.createAnswer();
            await pc.setLocalDescription(answer);
            send({
              type: "signal",
              to: peerId,
              from: myId,
              sala: room,
              payload: answer
            });
          } else if (payload.type === "answer") {
            await pc.setRemoteDescription(new RTCSessionDescription(payload));
          } else if (payload.type === "candidate") {
            if (payload.candidate) {
              await pc.addIceCandidate(new RTCIceCandidate(payload.candidate));
            }
          }
        } else if (msg.type === "peer-left") {
          const peerId = msg.id;
          if (peers[peerId]) {
            peers[peerId].close();
            delete peers[peerId];
          }
          removeRemoteVideo(peerId);
        }
      } catch (err) {
        alert("Erro ao processar mensagem do servidor: " + err.message);
        console.error("Erro onmessage:", err, event.data);
      }
    };
  }

  // Agora o botão "Iniciar Aula" chama a função que inicia tudo
  document.getElementById("btnStart").addEventListener("click", async () => {
    await startTransmission();
  });

  // Você pode adicionar aqui os eventos dos botões de áudio e vídeo também
  // Por exemplo:
  const btnAudio = document.getElementById("btnAudio");
  btnAudio.addEventListener("click", () => {
    if (localStream) {
      localStream.getAudioTracks().forEach(track => track.enabled = !track.enabled);
      btnAudio.style.backgroundColor = localStream.getAudioTracks()[0].enabled ? "black" : "#700";
    }
  });

  const btnVideo = document.getElementById("btnVideo");
  btnVideo.addEventListener("click", () => {
    if (localStream) {
      localStream.getVideoTracks().forEach(track => track.enabled = !track.enabled);
      btnVideo.style.backgroundColor = localStream.getVideoTracks()[0].enabled ? "black" : "#700";
    }
  });

    document.getElementById("btnEndCall")?.addEventListener("click", () => {

    // Envia uma mensagem para a janela que abriu esta
    window.opener?.postMessage("end-call", "*");

    // Fecha a janela atual (meet.html)
    window.close();
  });
