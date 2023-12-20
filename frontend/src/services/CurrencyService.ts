import { REACT_APP_UNIVERSAL_LINK } from "@env";
import { ChartData } from "react-native-chart-kit/dist/HelperTypes";
import { CurrencyChart } from "../type/CurrencyChart";
import { TokenService } from "./TokenService";
import { subMonths, format, subDays, parseISO } from "date-fns";

export class CurrencyService {
  universalLink: string = REACT_APP_UNIVERSAL_LINK;
  getAllCurrenciesLink: string =
    this.universalLink + ":8080/api/v1/currency/rate";
  getCurrencyDataWeeklyLink: string =
    this.universalLink + ":8080/api/v1/currency/rate/week/";
  getCurrencyDataMonthlyLink: string =
    this.universalLink + ":8080/api/v1/currency/rate/month/";
  getCurrencyDataYearlyLink: string =
    this.universalLink + ":8080/api/v1/currency/rate/year/";

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

  convertData(data: CurrencyChart[], type: string) {
    const labels: string[] = [];
    const datasetData: number[] = [];

    switch (type) {
      case "week":
        for (const item of data) {
          const date = parseISO(item.effectiveDate);
          const dayOfWeek = date.getDay();
          if (dayOfWeek !== 0 && dayOfWeek !== 6) {
            labels.push(format(date, "EEE"));
          }
        }
        break;

      case "month":
        for (let i = 0; i < data.length; i += 3) {
          const item = data[i];
          const date = parseISO(item.effectiveDate);
          labels.push(format(date, "dd-MM"));
        }
        break;

      case "year":
        const uniqueMonths = [
          ...new Set(
            data.map((item) => parseISO(item.effectiveDate).getMonth())
          ),
        ];
        for (const month of uniqueMonths) {
          const date = new Date(2000, month, 1);
          labels.push(format(date, "MMM"));
        }
        break;

      default:
        break;
    }

    for (const item of data) {
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
        let test = this.convertData(data.list, "week");
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
        let test = this.convertData(data.list, "month");
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
        let test = this.convertData(data.list, "year");
        return test;
      })
      .catch((error) => {
        console.error(error);
        return error;
      });
  }
}
