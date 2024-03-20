# PokerClub REST API

## Methods

### Players

| Method   | URL         | Description                |
|----------|-------------|----------------------------|
| `GET`    | /players    | Retorna todos os jogadores |
| `GET`    | /players/id | Retorna um jogador         |
| `POST`   | /players    | Cria um jogador            |
| `PUT`    | /players/id | Atualiza um jogador        |
| `DELETE` | /players/id | Remove um jogador          |

### Tournament

| Method   | URL             | Description               |
|----------|-----------------|---------------------------|
| `GET`    | /tournaments    | Retorna todos os torneios |
| `GET`    | /tournaments/id | Retorna um torneio        |
| `POST`   | /tournaments    | Cria um torneio           |
| `PUT`    | /tournaments/id | Atualiza um torneio       |
| `DELETE` | /tournaments/id | Remove um torneio         |

### Tournament Players

| Method   | URL                            | Description                                          |
|----------|--------------------------------|------------------------------------------------------|
| `GET`    | /tournaments-players           | Retorna todos os registros de jogadores em torneios  |
| `GET`    | /tournaments-players/player/id | Retorna todos os registros de um jogador em torneios |
| `POST`   | /tournaments-players           | Cria um registro de um jogador em um torneio         |
| `PUT`    | /tournaments-players/id        | Atualiza um registro de um jogador de um torneio     |
| `DELETE` | /tournaments-players/id        | Remove um registro de um jogador de um torneio       |