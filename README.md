### Group project with [@GGGeralt][GGGeralt], [@SARUXSARU][SARUXSARU], [@GoldFireGames][GoldFireGames] and [@KubaPlayer1][KubaPlayer1]

---

#### The repository of project for one of the course at University of Lodz

---

#### The assumptions were:

- imitate Agile / Scrum (daily, scrum roles like scrum master and product owner)
- work in group (pr reviews, supporting each other)
- use Trello Board for tasks management
- build someting cool!

---

#### Project info:

- use [NBP API][nbpApi] to build exchange currency simulator / app

---

#### Stack we used:

- Java 17
- Spring Boot 3
- Typescript / Javascript
- React Native
- MongoDB
- Maven 
- Junit
- Expo
- Postman

---

#### Roles in team:
- [@TrueJacobG][TrueJacobG]: Frontend, Backend, Tester, Scrum master (kinda), Product Owner (kinda) 
- [@GGGeralt][GGGeralt]: Backend, Frontend
- [@SARUXSARU][SARUXSARU]: Database, Backend, Tester
- [@GoldFireGames][GoldFireGames]: Tester, Backend
- [@KubaPlayer1][KubaPlayer1]: Frontend

---

#### Future improvements / fixes:
- Redis: for caching currency codes, currency rates etc.
- Docker + Jenkins: for CI/CD and testing
- Frontend fixes: lack of the major error handlings, lack of animation, probably add [TamaGUI][tamagui] for visual improvements
- Security: token validations and in the futre use fe. google oauth service
- Maybe try to deploy on AWS + Expo

---

#### Installation

Backend:
```
cd ./currencyhub

cd ./src/main/resources
<add application.yml as application.yml.example says> 

mvn clean install
mvn spring-boot:run
```

Frontend
```
cd ./frontend
npm install
npm run start
```

[nbpApi]: https://api.nbp.pl/en.html
[tamagui]: https://tamagui.dev/
[TrueJacobG]: https://github.com/TrueJacobG
[GGGeralt]: https://github.com/GGGeralt
[SARUXSARU]: https://github.com/SARUXSARU
[GoldFireGames]: https://github.com/GoldFireGames
[KubaPlayer1]: https://github.com/KubaPlayer1
