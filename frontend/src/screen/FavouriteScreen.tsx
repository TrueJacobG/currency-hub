import AsyncStorage from "@react-native-async-storage/async-storage";
import { Link } from "react-router-native";
import { View, Text, StyleSheet } from "react-native";
import FavouriteListComponent from "../components/currency/FavouriteListComponent";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { FavouriteService } from "../services/FavouriteService";
import { CurrencyService } from "../services/CurrencyService";
import { Currency } from "../type/Currency";
import { User } from "../type/User";
import { useAtom } from "jotai";
import { useEffect, useState } from "react";

const FavouriteScreen = () => {
  const favouriteService = new FavouriteService();
  const currencyService = new CurrencyService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  const [favourites, setFavourites] = useState<Currency[]>([]);
  const [currencies, setCurrencies] = useState<Currency[]>([]);

  useEffect(() => {
    favouriteService
      .getUserFavourites()
      .then((data) => {
        setFavourites(data.list);
      })
      .catch((error) => {
        console.error(error);
      });
    currencyService
      .getAllCurrencies()
      .then((data) => {
        setCurrencies(data.list);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  function addFunc(cur: Currency) {
    if (!favourites.some((d) => cur.currencyCode === d.currencyCode)) {
      setFavourites(favourites.concat(cur));
    }
  }

  function delFunc(cur: Currency) {
    const newList = favourites.filter(
      (item) => item.currencyCode != cur.currencyCode
    );

    setFavourites(newList);
  }

  return (
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to your favourites!</Text>
        <View>
          <Text>Email: {loggedUser.email}</Text>
        </View>
        <View>
          <FavouriteListComponent
            currencies={favourites}
            action="Delete"
            onButtonPress={delFunc}
          />
          <View style={styles.space}></View>
          <FavouriteListComponent
            currencies={currencies}
            action="Add"
            onButtonPress={addFunc}
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
  space: {
    borderBottomColor: "black",
    borderBottomWidth: StyleSheet.hairlineWidth,
  },
});
