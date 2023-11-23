import { LoginUser } from "../type/LoginUser";
import { User } from "../type/User";

export class AuthorizationService {
  registerUserLink: string = "http://172.20.10.3:8080/api/v1/user/";
  loginUserLink: string = "http://172.20.10.3:8080/api/v1/user/";

  exampleToken: string =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFUiLCJwYXNzd29yZCI6InRlc3QxMjMxMjMxMjMifQ.bHmBljA0uPuObIW2HfF9a4TTV1X_xvo7swSDwL6qywc";

  getToken() {
    // TODO:
    // add endpoint that will create token on register and login
  }

  async registerUser(user: User) {
    // TODO:
    // get token for register

    return await fetch(this.registerUserLink, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: this.exampleToken,
      },
      body: JSON.stringify(user),
    })
      .then((response) => response.json())
      .then((data) => {
        return data;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }

  async loginUser(user: LoginUser) {
    // TODO:
    // get token for login

    return await fetch(this.loginUserLink + user.email, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: this.exampleToken,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        return data;
      })
      .catch((error) => {
        console.error(error);
        console.log("ASDA");
        return error;
      });
  }
}
