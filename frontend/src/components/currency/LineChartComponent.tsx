import React from "react";
import { View, StyleSheet, Dimensions } from "react-native";
import { LineChart } from 'react-native-chart-kit'
import { ChartData } from 'react-native-chart-kit/dist/HelperTypes';
import { getChartData } from "../../services/ChartDataProvider";

type Props = { currencyCode: string; timeRange: string; onCloseModal: () => void };

const LineChartComponent = ({ currencyCode, timeRange, onCloseModal }: Props) => {
  const [chartData, setChartData] = React.useState<ChartData>({ labels: [], datasets: [{ data: [] }] });

  React.useEffect(() => {
    const fetchData = async () => {
      const data = await getChartData(currencyCode, timeRange);
      setChartData(data);
    };

    fetchData();
  }, [currencyCode, timeRange]);

  return (
    <View style={styles.chartContainer}>
      <LineChart
        data={chartData}
        width={Dimensions.get("window").width}
        height={Dimensions.get("window").height * 0.6}
        yAxisSuffix="Zł"
        yAxisInterval={1}
        chartConfig={{
          backgroundColor: "#fff",
          backgroundGradientFrom: "#fff",
          backgroundGradientTo: "#fff",
          decimalPlaces: 2,
          color: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
          labelColor: (opacity = 1) => `rgba(0, 0, 0, ${opacity})`,
          style: {
            borderRadius: 10,
          },
          propsForDots: {
            r: "3",
            strokeWidth: "0.5",
            stroke: "#ffa726",
          },
        }}
        bezier
        style={{ marginVertical: 8, borderRadius: 10 }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  chartContainer: {
    justifyContent: "center",
    alignItems: "center",
  },
});

export default LineChartComponent;