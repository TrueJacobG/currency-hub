import React, { useEffect, useState } from "react";
import {
  Button,
  Modal,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from "react-native";
import { ChartData } from "react-native-chart-kit/dist/HelperTypes";
import { CurrencyService } from "../../services/CurrencyService";
import { Currency } from "../../type/Currency";
import LineChartComponent from "./LineChartComponent";
import { FavouriteService } from "../../services/FavouriteService";
import { useNavigation } from "@react-navigation/native";

type Props = {
  currency: Currency;
  onCloseModal: () => void;
  onButtonPress: string;
};

const FavouriteElementComponent = ({
  currency,
  onCloseModal,
  onButtonPress,
}: Props) => {
  const currencyService = new CurrencyService();
  const favouriteService = new FavouriteService();
  const navigation = useNavigation();

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

  const favouriteAddDelete = async (currencyCode: string) => {
    switch (onButtonPress) {
      case "Add":
        favouriteService.AddUserFavourite(currencyCode).catch((error) => {
          console.error(error);
        });
        break;
      case "Delete":
        favouriteService
          .DeleteUserFavourite(currency.currencyCode)
          .catch((error) => {
            console.error(error);
          });
        break;
      default:
        console.error("Add or Delete");
    }
  };

  return (
    <View style={styles.currencyContainer}>
      <TouchableOpacity onPress={toggleModal}>
        <Text>{currency.currencyCode}</Text>
        <Text>{currency.currencyName}</Text>
        <Text>{currency.mid}</Text>
      </TouchableOpacity>
      <Button
        title={onButtonPress}
        onPress={() => {
          favouriteAddDelete(currency.currencyCode);
        }}
      ></Button>

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
              <Button
                title="Follow"
                onPress={() => {
                  favouriteAddDelete(currency.currencyCode);
                }}
              />
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
});

export default FavouriteElementComponent;
