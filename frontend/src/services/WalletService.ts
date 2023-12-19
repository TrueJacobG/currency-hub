import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { TokenService } from "./TokenService";
import { Double, Float } from "react-native/Libraries/Types/CodegenTypes";

export class WalletService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getUserWalletLink: string = this.universalLink + ":8080/api/v1/wallet/";
  addCashLink: string = this.universalLink + ":8080/api/v1/wallet/transfer";

  tokenService = new TokenService();

  async getUserWallet() {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });
    //TODO:: spojrz tu, problwalem naprawic zamieniajc map na 2 listy, nie wiedzialem jak
    let wallet = {
      codes: [],
      values: Number,
    };

    return await fetch(this.getUserWalletLink, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        return data.walletMap;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }

  async addCash(plnAmount: Float) {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.addCashLink, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify({
        value: plnAmount,
      }),
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
