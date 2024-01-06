import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { Float } from "react-native/Libraries/Types/CodegenTypes";
import { TokenService } from "./TokenService";

export class WalletService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getUserWalletLink: string = this.universalLink + ":8080/api/v1/wallet/";
  addCashLink: string = this.universalLink + ":8080/api/v1/wallet/transfer";
  exchangeLink: string = this.universalLink + ":8080/api/v1/wallet/exchange";

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
        return data.walleList;
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
        return data.newWalletValue;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }

  async exchange(
    currencyCodeFrom: String,
    currencyCodeTo: String,
    value: Number
  ) {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.exchangeLink, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify({
        currencyCodeFrom: currencyCodeFrom,
        currencyCodeTo: currencyCodeTo,
        value: value,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        return data.message;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }
}
