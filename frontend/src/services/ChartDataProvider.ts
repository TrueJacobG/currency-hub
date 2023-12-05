import { ChartData } from 'react-native-chart-kit/dist/HelperTypes';
import { format, parseISO } from 'date-fns';
import { apiUrl, exampleToken } from './Config';

export const getChartData = async (currencyCode: string, timeRange: string): Promise<ChartData> => {
  let endpoint: string;

  switch (timeRange) {
    case "Week":
      endpoint = `${apiUrl}/${currencyCode}/week`;
      break;
    case "Month":
      endpoint = `${apiUrl}/${currencyCode}/month`;
      break;
    case "Year":
      endpoint = `${apiUrl}/${currencyCode}/year`;
      break;
    default:
      return {
        labels: [],
        datasets: [{ data: [] }],
      };
  }

  try {
    const response = await fetch(endpoint, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: exampleToken,
      },
    });

    const data = await response.json();

    if (data && Array.isArray(data.list)) {
      const labels: string[] = [];
      const datasetData: number[] = [];

      for (const item of data.list) {
        const date = parseISO(item.effectiveDate);
        const formattedDate = format(date, 'yyyy-MM-dd');

        labels.push(formattedDate);
        datasetData.push(item.mid);
      }

      const chartData: ChartData = {
        labels: labels,
        datasets: [{ data: datasetData }],
      };

      return chartData;
    } else {
      console.error("Invalid data structure:", data);
      return {
        labels: [],
        datasets: [{ data: [] }],
      };
    }
  } catch (error) {
    console.error(error);
    return {
      labels: [],
      datasets: [{ data: [] }],
    };
  }
};