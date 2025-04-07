const API_URL = 'http://localhost:8080/produtos';

function carregarProdutos() {
    const tabela = document.getElementById('tabela-produtos');
    const loader = document.getElementById('loader');
    const produtosContainer = document.querySelector('.produtos');

    loader.style.display = 'block';
    tabela.innerHTML = '';  

    fetch(API_URL)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            data.forEach((produto) => {
                const linha = document.createElement('tr');
                linha.classList.add('align-middle');
                linha.innerHTML = `
                    <td width="150px" class="text-center"><img src="${produto.urlImg}" alt="${produto.nome}" width="100px"></td>
                    <td class="text-end">${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td class="text-end">${produto.preco.toFixed(2)}</td>
                `;
                tabela.appendChild(linha);
            });
            loader.style.display = 'none';
        })
        .catch(error => {
            loader.style.display = 'none';
            produtosContainer.innerHTML += `
                <div class="error-screen">
                    <h1>Oops!</h1>
                    <p>Não foi possível acessar os produtos 😢</p>
                    <p>Erro: ${error.message}</p>
                    <a href="index.html" class="btn btn-danger mt-4">Tentar novamente</a>
                </div>
            `;
        });
}

document.getElementById('form-produto').addEventListener('submit', function (e) {
    e.preventDefault();

    const nome = document.getElementById('nome').value;
    const preco = parseFloat(document.getElementById('preco').value);
    const urlImg = document.getElementById('urlImg').value;

    fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ nome, preco, urlImg })
    })
        .then(response => {
            if (!response.ok) throw new Error('Erro ao cadastrar produto');
            return response.json();
        })
        .then(data => {
            console.log('Produto cadastrado:', data);
            this.reset();
            carregarProdutos();
        })
        .catch(err => console.error('Erro no POST:', err));
});

carregarProdutos();
