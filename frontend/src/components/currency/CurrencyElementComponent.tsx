import React, { useEffect, useState } from "react";
import {
  Button,
  Modal,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
  Image,
} from "react-native";
import { ChartData } from "react-native-chart-kit/dist/HelperTypes";
import { CurrencyService } from "../../services/CurrencyService";
import { Currency } from "../../type/Currency";
import LineChartComponent from "./LineChartComponent";
import { MaterialCommunityIcons } from "@expo/vector-icons";

type Props = { currency: Currency; onCloseModal: () => void };

const CurrencyElementComponent = ({ currency, onCloseModal }: Props) => {
  const currencyService = new CurrencyService();

  const [isModalVisible, setModalVisible] = useState(false);
  const [selectedTimeRange, setSelectedTimeRange] = useState("Week");

  const toggleModal = () => {
    setModalVisible(!isModalVisible);
    onCloseModal();
  };

  const [chartData, setChartData] = useState<ChartData>({
    labels: [],
    datasets: [{ data: [] }],
  });

  useEffect(() => {
    switch (selectedTimeRange) {
      case "Week":
        currencyService
          .getCurrencyDataWeekly(currency.currencyCode)
          .then((data) => {
            setChartData(data);
          });
        break;
      case "Month":
        currencyService
          .getCurrencyDataMonthly(currency.currencyCode)
          .then((data) => {
            setChartData(data);
          });
        break;
      case "Year":
        currencyService
          .getCurrencyDataYearly(currency.currencyCode)
          .then((data) => {
            setChartData(data);
          });
        break;
      default:
        currencyService
          .getCurrencyDataWeekly(currency.currencyCode)
          .then((data) => {
            setChartData(data);
          });
        break;
    }
  }, [selectedTimeRange]);

  const renderArrow = () => {
    if (currency.pointerUP !== undefined) {
      const iconName = currency.pointerUP ? "trending-up" : "trending-down";
      const iconColor = currency.pointerUP ? "green" : "red";
  
      return (
        <MaterialCommunityIcons
          name={iconName}
          size={40}
          color={iconColor}
          style={styles.arrowIcon}
        />
      );
    }
    return null;
  };

  return (
    <View style={styles.currencyContainer}>
      <TouchableOpacity onPress={toggleModal}>
        <Text>{currency.currencyCode}</Text>
        <Text>{currency.currencyName}</Text>
        <Text>{currency.mid}</Text>
      </TouchableOpacity>
      {renderArrow()}

      <Modal animationType="slide" transparent={true} visible={isModalVisible}>
        <View style={styles.modalContainer}>
          <View style={styles.modalContent}>
            <View style={styles.choiceButtons}>
              <Button
                title="Week"
                onPress={() => setSelectedTimeRange("Week")}
              />
              <Button
                title="Month"
                onPress={() => setSelectedTimeRange("Month")}
              />
              <Button
                title="Year"
                onPress={() => setSelectedTimeRange("Year")}
              />
            </View>
            <LineChartComponent chartData={chartData} />
            <View style={styles.buttonContainer}>
              <Button title="Follow" onPress={() => {}} />
              <Button title="Close Modal" onPress={toggleModal} />
            </View>
          </View>
        </View>
      </Modal>
    </View>
  );
};

const styles = StyleSheet.create({
  currencyContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    paddingHorizontal: 16,
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderBottomColor: "#ccc",
  },
  modalContainer: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "rgba(0,0,0,0.5)",
  },
  modalContent: {
    width: "100%",
    height: "77%",
    backgroundColor: "white",
    padding: 20,
    borderRadius: 10,
  },
  buttonContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 5,
  },
  choiceButtons: {
    flexDirection: "row-reverse",
    justifyContent: "space-between",
    marginBottom: 16,
  },
  arrowIcon: {
    width: 40,
    height: 40,
    marginTop: 10,
  },
});

export default CurrencyElementComponent;
