import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import CurrencyListComponent from "../components/currency/CurrencyListComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { CurrencyService } from "../services/CurrencyService";
import { Currency } from "../type/Currency";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Home">;

const BaseScreen = ({ navigation }: Props) => {
  const currencyService = new CurrencyService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  const [currencies, setCurrencies] = useState<Currency[]>([]);

  useEffect(() => {
    console.log("test");
    currencyService
      .getAllCurrencies()
      .then((data) => {
        console.log(data);
        setCurrencies(data.list);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <View>
      <Text style={styles.textintro}>Welcome to CurrencyHub!</Text>
      <Text style={styles.text}>This is not your money any more</Text>
      <View>
        <Text>Email: {loggedUser.email}</Text>
      </View>
      <View>
        <CurrencyListComponent currencies={currencies} />
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
});
