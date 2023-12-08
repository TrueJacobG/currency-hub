import { REACT_APP_UNIVERSAL_LINK } from "@env"

export class FavouriteService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getUserFavouritesLink: string = this.universalLink + ":8080/api/v1/favourite/";

  exampleToken: string =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFUiLCJwYXNzd29yZCI6InRlc3QxMjMxMjMxMjMifQ.bHmBljA0uPuObIW2HfF9a4TTV1X_xvo7swSDwL6qywc";

  async getUserFavourites() {
    return await fetch(this.getUserFavouritesLink, {
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
