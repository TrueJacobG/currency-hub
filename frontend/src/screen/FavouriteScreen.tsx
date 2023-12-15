import AsyncStorage from "@react-native-async-storage/async-storage";
import { Link } from "react-router-native";
import { View, Text, StyleSheet } from "react-native";
import CurrencyListComponent from "../components/currency/CurrencyListComponent";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { FavouriteService } from "../services/FavouriteService";
import { Currency } from "../type/Currency";
import { User } from "../type/User";
import { useAtom } from "jotai";
import { useEffect, useState } from "react";

const FavouriteScreen = () => {
  const favouriteService = new FavouriteService();
  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);
  const [currencies, setCurrencies] = useState<Currency[]>([]);
  const [selectedCurrency, setSelectedCurrency] = useState<Currency | null>(
    null
  );

  useEffect(() => {
    favouriteService
      .getUserFavourites()
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
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to your favourites!</Text>
        <View>
          <Text>Email: {loggedUser.email}</Text>
        </View>
        <View>
          <CurrencyListComponent
            currencies={currencies}
            onCurrencyPress={handleCurrencyPress}
          />
        </View>
      </View>
      <View style={styles.bottomView}>
        <MenuComponent />
      </View>
    </View>
  );
};

export default FavouriteScreen;

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