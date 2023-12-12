import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { ChartData } from "react-native-chart-kit/dist/HelperTypes";
import { CurrencyChart } from "../type/CurrencyChart";
import { TokenService } from "./TokenService";

export class CurrencyService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getAllCurrenciesLink: string = this.universalLink + ":8080/api/v1/currency/rate";
  getCurrencyDataWeeklyLink: string = this.universalLink + ":8080/api/v1/currency/rate/week/";
  getCurrencyDataMonthlyLink: string = this.universalLink + ":8080/api/v1/currency/rate/month/";
  getCurrencyDataYearlyLink: string = this.universalLink + ":8080/api/v1/currency/rate/year/";

  tokenService = new TokenService();

  async getAllCurrencies() {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.getAllCurrenciesLink, {
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

  convertData(data: CurrencyChart[]) {
    const labels: string[] = [];
    const datasetData: number[] = [];

    for (const item of data) {
      labels.push(item.effectiveDate);
      datasetData.push(item.mid);
    }

    const chartData: ChartData = {
      labels: labels,
      datasets: [{ data: [...datasetData] }],
    };

    return chartData;
  }

  async getCurrencyDataWeekly(currencyCode: string): Promise<ChartData> {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.getCurrencyDataWeeklyLink + currencyCode, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        let test = this.convertData(data.list);
        return test;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }

  async getCurrencyDataMonthly(currencyCode: string): Promise<ChartData> {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.getCurrencyDataMonthlyLink + currencyCode, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        let test = this.convertData(data.list);
        return test;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }

  async getCurrencyDataYearly(currencyCode: string): Promise<ChartData> {
    let token = await this.tokenService.getToken().then((t) => {
      return t;
    });

    return await fetch(this.getCurrencyDataYearlyLink + currencyCode, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        let test = this.convertData(data.list);
        return test;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }
}
