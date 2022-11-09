# MeliFrescos! 🍅🌽🍓️🥬



> Aplicação para cadastro e gerenciamento de dados de um site de vendas de produtos frescos, congelados
> e refrigerados, que se encontram em armazéns e setores diferentes, e que podem ser adquiridos
> por consumidores através de um carrinho de compras.

### 📈 Principais funcionalidades

O projeto trata-se de uma atividade avaliativa da Digital House em parceria com o Mercado Livre e possui alguns
requisitos, sendo:

- [ ] Cadastro de Lotes de Produtos
- [ ] Consulta e atualização do lote de produtos
- [ ] Consulta da lista de Produtos disponível
- [ ] Consulta da lista de Produtos por categoria (Congelados, Frescos e Resfriados)
- [ ] Registro de um pedido de um determinado consumidor
- [ ] Consulta do pedido de compra
- [ ] Alteração do pedido de compra

⏳ Novos requisitos são disponibilizados diariamente, por isso esta lista 
sofrerá alterações.


### 💻 Informações relevantes

Este projeto utilizará as seguintes tecnologias:


<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" width="100px;" alt="Foto Java"/><br>
        <sub>
          <b> Java </b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" width="100px;" alt="Foto Spring Boot"/><br>
        <sub>
          <b>Spring Boot</b>
        </sub>
      </a>
    </td>
 <td align="center">
      <a href="#">
        <img src="https://raw.githubusercontent.com/github/explore/master/topics/git/git.png" width="100px;" alt="Foto Spring Boot"/><br>
        <sub>
          <b>Git</b>
        </sub>
      </a>
    </td>
 <td align="center">
      <a href="#">
        <img src="https://raw.githubusercontent.com/github/explore/master/topics/sql/sql.png" width="100px;" alt="Foto Sql"/><br>
        <sub>
          <b>MySql</b>
        </sub>
      </a>
    </td>
 <td align="center">
      <a href="#">
        <img src="https://raw.githubusercontent.com/github/explore/master/topics/docker/docker.png" width="100px;" alt="Docker"/><br>
        <sub>
          <b>Docker</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="#">
        <img src="https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" width="100px;" alt="Foto Maven"/><br>
        <sub>
          <b>Maven</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

### 💻 Executar o projeto


Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```

Após executar o comando acima, basta apenas abrir o seguinte endereço e visualizar a execução do projeto:

```
http://localhost:1001
```

### Funcionalidades  🖥
- Cadastro - POST
- Apagar - DELETE
- Busca - GET
- Busca por ID do produto- GET
- Busca por categoria - GET
- Alteração de todos os dados- PUT
- Alteração de parte dos dados - PATCH

### Modelo com campos obrigatórios para teste  📩

```
{
	"purchaseOrder": {
		"date": "LocalDate",
		"buyerId": "Long",
		"orderStatus": "String",
		"products": [{
				"productId": "Long",
				"quantity": "int"
			},
			{
				"productId": "Long",
				"quantity": "int"
			}
		]
	}
}
```


## Projeto desenvolvido por:

- Amanda Gomes Lobo 🍅
- Fernanda Alcionê de Souza 🍉
- Gustavo Walter Bartel Dolzan 🍒
- Igor Silva Fernandes 🍇
- Leonardo Henrique Correia dos Santos 🥝
- Thaíssa Carrafa do Nascimento 🍓
