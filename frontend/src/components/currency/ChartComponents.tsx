import { View, Text, Dimensions, Button } from "react-native";
import React from "react";
import { LineChart } from "react-native-chart-kit";
import { ChartData } from "react-native-chart-kit/dist/HelperTypes";

type Props = { data: ChartData };

const ChartComponent = ({ data }: Props) => {
  const withChar = () => {};
  return (
    <View>
      <Text>
        <Button onPress={withChar} title="1 Week" />
        <Button onPress={withChar} title="1 Month" />
        <Button onPress={withChar} title="1 Year" />
      </Text>
      <LineChart
        data={data}
        width={Dimensions.get("window").width}
        height={200}
        yAxisLabel={"Zł"}
        chartConfig={{
          backgroundGradientFrom: "darkblue",
          backgroundGradientTo: "blue",
          color: (opacity = 3) => `rgba(255, 255, 255, ${opacity})`,
        }}
      />
    </View>
  );
};

export default ChartComponent;
