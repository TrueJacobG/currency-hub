import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { TokenService } from "./TokenService";

export class WalletService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getUserWalletLink: string = this.universalLink + ":8080/api/v1/wallet/";

  tokenService = new TokenService();

  async getUserWallet() {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.getUserWalletLink, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
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
