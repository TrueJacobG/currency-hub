import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { TokenService } from "./TokenService";

export class FavouriteService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  userFavouritesLink: string = this.universalLink + ":8080/api/v1/favourite/";

  tokenService = new TokenService();

  async getUserFavourites() {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.userFavouritesLink, {
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

  async DeleteUserFavourite(currencyCode: string) {
    this.userFavouritesLink += currencyCode;

    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.userFavouritesLink, {
      method: "DELETE",
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

  async AddUserFavourite(currencyCode: string) {
    this.userFavouritesLink += currencyCode;

    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.userFavouritesLink, {
      method: "POST",
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
