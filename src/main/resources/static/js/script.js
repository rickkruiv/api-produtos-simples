const API_URL = 'http://localhost:8080/produtos';

function carregarProdutos() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            const tabela = document.getElementById('tabela-produtos');
            tabela.innerHTML = '';
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
        })
        .catch(err => console.error('Erro ao buscar produtos:', err));
}

document.getElementById('form-produto').addEventListener('submit', function (e) {
    e.preventDefault();

    const nome = document.getElementById('nome').value;
    const preco = parseFloat(document.getElementById('preco').value);
    const urlImg = document.getElementById('urlImg').value;

    fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
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