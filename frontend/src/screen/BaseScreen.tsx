import AsyncStorage from "@react-native-async-storage/async-storage";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import CurrencyElementComponent from "../components/currency/CurrencyElementComponent";
import CurrencyListComponent from "../components/currency/CurrencyListComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { CurrencyService } from "../services/CurrencyService";
import { Currency } from "../type/Currency";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";
import MenuComponent from "../components/menu/MenuComponent";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Home">;

const BaseScreen = ({ navigation }: Props) => {
  const currencyService = new CurrencyService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  const [currencies, setCurrencies] = useState<Currency[]>([]);
  const [selectedCurrency, setSelectedCurrency] = useState<Currency | null>(
    null
  );

  useEffect(() => {
    currencyService
      .getAllCurrencies()
      .then((data) => {
        setCurrencies(data.list);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const getData = async () => {
    let data = await AsyncStorage.getItem("token");
    console.log(data);
  };

  useEffect(() => {
    getData();
  }, []);

  const handleCurrencyPress = (currency: Currency) => {
    setSelectedCurrency(currency);
  };

  const handleModalClose = () => {
    setSelectedCurrency(null);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.textintro}>Welcome to CurrencyHub!</Text>
      <Text style={styles.text}>This is not your money anymore</Text>
      <View>
        <Text>Email: {loggedUser.email}</Text>
        <Text>{}</Text>
      </View>
      <CurrencyListComponent
        currencies={currencies}
        onCurrencyPress={handleCurrencyPress}
      />
      {selectedCurrency && (
        <CurrencyElementComponent
          currency={selectedCurrency}
          onCloseModal={handleModalClose}
        />
      )}
      <View style={styles.bottomView}>
        <MenuComponent />
      </View>
    </View>
  );
};

export default BaseScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  textintro: {
    textAlign: "center",
    fontSize: 40,
    marginTop: 20,
  },
  text: {
    textAlign: "center",
    fontSize: 10,
    marginBottom: 40,
  },
  bottomView: {
    alignItems: "center",
    marginTop: "auto",
  },
});
