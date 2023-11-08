import { LoginUser } from "../type/LoginUser";
import { User } from "../type/User";

export class AuthorizationService {
  registerUserLink: string = "http://192.168.0.18:8080/api/v1/user/";
  loginUserLink: string = "http://192.168.0.18:8080/api/v1/user/";

  exampleToken: string =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFVzZXJuYW1lIiwicGFzc3dvcmQiOiIkMmEkMTIkRG5LSGppSDlRY2xDVE54YnE5N3RLdU9VR2VIL045MFNHL20uR0ZkTTVWMHJJc1ZJa1gwd0cifQ.qeCf0PQOqkKuuYr7ecDokSTKRin-ucVPLeIPDTT8ZNQ";

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
        return error;
      });
  }
}
