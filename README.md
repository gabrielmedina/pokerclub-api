# PokerClub REST API

## Methods

### Players

| Method   | URL           | Description                |
|----------|---------------|----------------------------|
| `GET`    | /players      | Retorna todos os jogadores |
| `GET`    | /players/{id} | Retorna um jogador         |
| `POST`   | /players      | Cria um jogador            |
| `PUT`    | /players/{id} | Atualiza um jogador        |
| `DELETE` | /players/{id} | Remove um jogador          |

### Tournament

| Method   | URL               | Description               |
|----------|-------------------|---------------------------|
| `GET`    | /tournaments      | Retorna todos os torneios |
| `GET`    | /tournaments/{id} | Retorna um torneio        |
| `POST`   | /tournaments      | Cria um torneio           |
| `PUT`    | /tournaments/{id} | Atualiza um torneio       |
| `DELETE` | /tournaments/{id} | Remove um torneio         |

### Tournament Players

| Method   | URL                                                               | Description                                                         |
|----------|-------------------------------------------------------------------|---------------------------------------------------------------------|
| `GET`    | /tournaments-players                                              | Retorna todos os registros de torneios e jogadores                  |
| `GET`    | /tournaments-players/player/{playerId}                            | Retorna todos os registros de um jogador e seus torneios            |
| `GET`    | /tournaments-players/tournament/{tournamentId}                    | Retorna todos os registros de um torneio e seus jogadores           |
| `GET`    | /tournaments-players/tournament/{tournamentId}/player/{playerId}  | Retorna o registro de um torneio que teve a participação do jogador |
| `POST`   | /tournaments-players/tournament/{tournamentId}/player/{playerId}  | Cria um registro de um jogador em um torneio                        |
| `PUT`    | /tournaments-players/tournament/{tournamentId}/player/{playerId}  | Atualiza um registro de um jogador de um torneio                    |
| `DELETE` | /tournaments-players/tournament/{tournamentId}/player/{playerId}  | Remove um registro de um jogador de um torneio                      |