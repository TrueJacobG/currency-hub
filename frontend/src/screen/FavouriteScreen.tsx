import AsyncStorage from "@react-native-async-storage/async-storage";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import React, { useEffect, useState } from "react";
import { StyleSheet, Text, View } from "react-native";
import CurrencyListComponent from "../components/currency/CurrencyListComponent";
import MenuComponent from "../components/menu/MenuComponent";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { FavouriteService } from "../services/FavouriteService";
import { Currency } from "../type/Currency";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Favourite">;

const FavouriteScreen = ({ navigation }: Props) => {
  const favouriteService = new FavouriteService();

  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  const [currencies, setCurrencies] = useState<Currency[]>([]);

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

  const getData = async () => {
    let data = await AsyncStorage.getItem("token");
    console.log("WORKING " + data);
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <View>
      <View>
        <Text style={styles.textintro}>Welcome to ypur favourites!</Text>
        <View>
          <Text>Email: {loggedUser.email}</Text>
        </View>
        <View>
          <CurrencyListComponent currencies={currencies} />
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
