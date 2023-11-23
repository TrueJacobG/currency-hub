export class CurrencyService {
  getAllCurrenciesLink: string = "http://192.168.0.18:8080/api/v1/currency/rate";

  exampleToken: string =
    "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFVzZXJuYW1lIiwicGFzc3dvcmQiOiIkMmEkMTIkRG5LSGppSDlRY2xDVE54YnE5N3RLdU9VR2VIL045MFNHL20uR0ZkTTVWMHJJc1ZJa1gwd0cifQ.qeCf0PQOqkKuuYr7ecDokSTKRin-ucVPLeIPDTT8ZNQ";

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
