import React, { useEffect, useRef, useState } from "react";
import { ScrollView, StyleSheet, Text, View } from "react-native";
import { Link } from "react-router-native";
import CurrencyElementComponent from "../components/currency/CurrencyElementComponent";
import CurrencyListComponent from "../components/currency/CurrencyListComponent";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { CurrencyService } from "../services/CurrencyService";
import { Currency } from "../type/Currency";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";
import { useAtom } from "jotai";

const BaseScreen = () => {
  const currencyService = new CurrencyService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);
  console.log(loggedUser.email);

  const [currencies, setCurrencies] = useState<Currency[]>([]);
  const [selectedCurrency, setSelectedCurrency] = useState<Currency | null>(
    null
  );

  const scrollViewRef = useRef<ScrollView>(null);

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

  useEffect(() => {}, []);

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
      <ScrollView
        onContentSizeChange={() => scrollViewRef.current}
        ref={scrollViewRef}
      >
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
      </ScrollView>
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
    marginTop: 20,
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
