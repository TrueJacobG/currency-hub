import React from "react";
import { Dimensions, StyleSheet, View } from "react-native";
import { LineChart } from "react-native-chart-kit";
import { ChartData } from "react-native-chart-kit/dist/HelperTypes";

type Props = { chartData: ChartData };

const LineChartComponent = ({ chartData }: Props) => {
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
          decimalPlaces: 4,
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
          propsForBackgroundLines: {
            stroke: "rgba(0, 0, 0, 0.1)",
            strokeOpacity: 0.5,
          },
        }}
        bezier = {false}
        style={{ marginVertical: 8, borderRadius: 5 }}
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
