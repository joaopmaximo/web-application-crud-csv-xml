const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');
const nome = document.querySelector('#nome');
const email = document.querySelector('#email');
const senha = document.querySelector('#senha');
const tel = document.querySelector('#tel');
const formulario = document.querySelector('form');
const descricao = document.querySelector('#descricao');
const alterarSenha = document.querySelector('#alterarSenha');
alterarSenha.href = `./alterarSenha.html?id=${id}`;

function listar() {
    fetch(`http://localhost:8080/usuarios/${id}`)
    .then(response => response.json())
    .then (usuario => {
        nome.value = `${usuario.nome}`;
        senha.innerText = `${usuario.senha}`;
        email.value = `${usuario.email}`;
        tel.value = `${usuario.telefone}`;
    })
    .catch (error => console.error(error))
}

function atualizar() {
    return new Promise ((resolve, reject) => {
        fetch(`http://localhost:8080/usuarios/${id}`, {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: JSON.stringify ({
                nome: nome.value,
                email: email.value,
                telefone: tel.value
            }) // O body seria o conteudo da requisição, nesse caso ele usa o JSON.stringify para converter os dados para JSON
        })
        .then(response => {
            console.log(response);
            resolve(response);
        })
        .catch(error => reject(error))
    })
}

formulario.addEventListener ('submit', async function(event) {
    event.preventDefault()
    try {
        await atualizar()
        descricao.textContent = 'Usuário atualizado com sucesso!'
    }
    catch {
        descricao.textContent = 'Falha na atualização do usuário.'
    }
    listar()
})

listar()