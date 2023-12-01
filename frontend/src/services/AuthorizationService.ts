import AsyncStorage from "@react-native-async-storage/async-storage";
import { LoginUser } from "../type/LoginUser";
import { User } from "../type/User";



export class AuthorizationService {

  universalLink: string = "http://192.168.11.212"

  registerUserLink: string = this.universalLink + ":8080/api/v1/user/";
  loginUserLink: string = this.universalLink + ":8080/api/v1/user/";
  tokenRegisterLink: string = this.universalLink + ":8080/api/v1/auth/register";
  tokenLoginLink: string = this.universalLink + ":8080/api/v1/auth/login";

  exampleToken: string =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFUiLCJwYXNzd29yZCI6InRlc3QxMjMxMjMxMjMifQ.bHmBljA0uPuObIW2HfF9a4TTV1X_xvo7swSDwL6qywc";

  async getTokenRegister(name: string, authCode: string) {
    return await fetch(this.tokenRegisterLink, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: this.exampleToken,
      },
      body: JSON.stringify({ name: name, authCode: authCode }),
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        return data.token;
      })
      .catch((e) => {
        console.error(e);
      });
  }

  async getTokenLogin(email: string, authCode: string) {
    return await fetch(this.tokenLoginLink, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: this.exampleToken,
      },
      body: JSON.stringify({ email: email, authCode: authCode }),
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        return data.token;
      })
      .catch((e) => {
        console.error(e);
      });
  }

  async registerUser(user: User) {
    return await fetch(this.registerUserLink, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: this.exampleToken,
      },
      body: JSON.stringify(user),
    })
      .then((response) => response.json())
      .then(async (data) => {
        let token = await this.getTokenRegister(user.name, user.authCode);
        AsyncStorage.setItem("token", token);
        return data;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }

  async loginUser(user: LoginUser) {
    return await fetch(this.loginUserLink + user.email, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: this.exampleToken,
      },
    })
      .then((response) => response.json())
      .then(async (data) => {
        let token = await this.getTokenLogin(user.email, user.authCode);
        AsyncStorage.setItem("token", token);
        return data;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }
}
