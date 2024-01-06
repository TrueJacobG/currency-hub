import React, { useState } from "react";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { Currency } from "../../type/Currency";
import { FavouriteService } from "../../services/FavouriteService";
import { MaterialCommunityIcons } from "@expo/vector-icons";
import FavouriteScreen from "../../screen/FavouriteScreen";

type Props = {
  currency: Currency;
  action: string;
  onButtonPress: Function;
};

const FavouriteElementComponent = ({
  currency,
  action: onButtonPress,
  onButtonPress: func,
}: Props) => {
  const favouriteService = new FavouriteService();

  const favouriteAddDelete = async (currencyCode: string) => {
    switch (onButtonPress) {
      case "Add":
        favouriteService.addUserFavourite(currencyCode).catch((error) => {
          console.error(error);
        });
        func(currency);
        break;
      case "Delete":
        favouriteService
          .deleteUserFavourite(currency.currencyCode)
          .catch((error) => {
            console.error(error);
          });
        func(currency);
        break;
      default:
        console.error("[Add] or [Delete]");
    }
  };

  const handleImage = () => {
    switch (onButtonPress) {
      case "Add":
        return "heart";
      case "Delete":
        return "heart-broken";
    }
  };

  return (
    <View style={styles.currencyContainer}>
      <View>
        <Text>{currency.currencyCode}</Text>
        <Text>{currency.currencyName}</Text>
        <Text>{currency.mid}</Text>
      </View>
      <View style={styles.heartIcon}>
        <TouchableOpacity
          activeOpacity={0.5}
          onPress={() => {
            favouriteAddDelete(currency.currencyCode);
          }}
        >
          <MaterialCommunityIcons
            name={handleImage()}
            size={40}
            color={"red"}
          />
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  currencyContainer: {
    flexDirection: "row",
    paddingHorizontal: 16,
    paddingVertical: 8,
    borderBottomWidth: 1,
    borderBottomColor: "#ccc",
  },
  heartIcon: {
    aspectRatio: 1,
    resizeMode: "contain",
    alignItems: "flex-end",
    marginLeft: "auto",
  },
});

export default FavouriteElementComponent;
