export class CurrencyService {
  getAllCurrenciesLink: string = "http://10.102.0.185:8080/api/v1/currency/rate";

  exampleToken: string =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFUiLCJwYXNzd29yZCI6InRlc3QxMjMxMjMxMjMifQ.bHmBljA0uPuObIW2HfF9a4TTV1X_xvo7swSDwL6qywc";

  async getAllCurrencies() {
    return await fetch(this.getAllCurrenciesLink, {
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
