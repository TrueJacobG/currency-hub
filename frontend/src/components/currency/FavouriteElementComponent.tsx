import React, { useState } from "react";
import { Image, StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { CurrencyService } from "../../services/CurrencyService";
import { Currency } from "../../type/Currency";
import { FavouriteService } from "../../services/FavouriteService";
import { useNavigation } from "@react-navigation/native";

type Props = {
  currency: Currency;
  onButtonPress: string;
};

const FavouriteElementComponent = ({ currency, onButtonPress }: Props) => {
  const favouriteService = new FavouriteService();
  const heartImageLink = require("../../../assets/heart.png");
  const noHeartImageLink = require("../../../assets/noHeart.png");

  const favouriteAddDelete = async (currencyCode: string) => {
    switch (onButtonPress) {
      case "Add":
        favouriteService.addUserFavourite(currencyCode).catch((error) => {
          console.error(error);
        });
        break;
      case "Delete":
        favouriteService
          .deleteUserFavourite(currency.currencyCode)
          .catch((error) => {
            console.error(error);
          });
        break;
      default:
        console.error("[Add] or [Delete]");
    }
  };

  const handleImage = () => {
    switch (onButtonPress) {
      case "Add":
        return heartImageLink;
      case "Delete":
        return noHeartImageLink;
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
          <Image resizeMode="contain" source={handleImage()} />
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
    top: -15,
    marginBottom: -25,
    alignItems: "flex-end",
    marginLeft: "auto",
  },
});

export default FavouriteElementComponent;
