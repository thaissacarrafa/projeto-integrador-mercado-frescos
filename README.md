# MeliFrescos! 🍅🌽🍓️🥬



> Aplicação para cadastro e gerenciamento de dados de um site de vendas de produtos frescos, congelados
> e refrigerados, que se encontram em armazéns e setores diferentes, e que podem ser adquiridos
> por consumidores através de um carrinho de compras.

### 📈 Principais funcionalidades

O projeto trata-se de uma atividade avaliativa da Digital House em parceria com o Mercado Livre e possui alguns
requisitos, sendo:

- [x] Cadastro de Lotes de Produtos
- [x] Consulta e atualização do lote de produtos
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
http://localhost:8080
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

## Sugestão de melhoria 
 JWT (Json Web Token)
 
Visando seguraça e autenticidade do nosso sistema, é indispensavel o uso de um metodo de autenticação
para gerirmos quem terá acesso a determinadas partes da nossa API.<br/>
Com o JWT conseguimos gerar um token a partir de um objeto passado para ele,
esse objeto pode ser construído com os dados dos nosso indivíduos no registrado no banco. <br/>
sendo assim cada token gerado pode ter encriptado em si, qual o nível  de permissão de acesso 
do indivíduo no nosso sistema
 
 
### ao acessar o endpoint 
```
 POST http://localhost:8080/api/v1/auth
```

Passando o body login e senha no body da requisição
```

{
     "username": "eusoumanager@gmail.com",
     "password" :"123456"
}
```

Teremos  um token gerado devolvido no corpo da requisição 
```

{
    "access_token": "Bearer sdfghoiqy342o5iuherf8___example "
}
```

esse token deverá ser passado nos das proximas requisiçoes atravez da chave contina no
headers.Autorization

caso esse toke não seja passado teremos o seguinte retorno

```
   "message": "Invalid token",
   
```


## Projeto desenvolvido por:

- Amanda Gomes Lobo 🍅
- Fernanda Alcionê de Souza 🍉
- Gustavo Walter Bartel Dolzan 🍒
- Igor Silva Fernandes 🍇
- Leonardo Henrique Correia dos Santos 🥝
- Thaíssa Carrafa do Nascimento 🍓
