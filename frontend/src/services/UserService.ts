import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { TokenService } from "./TokenService";

export class UserService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getUserInfoLink: string = this.universalLink + ":8080/api/v1/user/";

  tokenService = new TokenService();

  async getUserInfo() {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.getUserInfoLink, {
      method: "GET",
      headers: {
        "Content-Type": "Bearer " + token,
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
