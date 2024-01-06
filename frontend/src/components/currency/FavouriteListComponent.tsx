import React from "react";
import { StyleSheet, Button, FlatList, View } from "react-native";
import { Currency } from "../../type/Currency";
import FavouriteElementComponent from "./FavouriteElementComponent";

type Props = {
  currencies: Currency[];
  onButtonPress: string;
  func: Function;
};

const FavouriteListComponent = ({ currencies, onButtonPress, func }: Props) => {
  return (
    <View>
      <FlatList
        data={currencies}
        renderItem={({ item }) => (
          <FavouriteElementComponent
            currency={item}
            onButtonPress={onButtonPress}
            func={func}
          ></FavouriteElementComponent>
        )}
        keyExtractor={(item) => item.currencyCode.toString()}
      ></FlatList>
    </View>
  );
};

export default FavouriteListComponent;

const styles = StyleSheet.create({
  container: {
    flexDirection: "row",
    marginRight: 0,
  },
});
