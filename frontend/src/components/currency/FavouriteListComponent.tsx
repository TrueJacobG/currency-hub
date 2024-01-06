import React from "react";
import { StyleSheet, Button, FlatList, View } from "react-native";
import { Currency } from "../../type/Currency";
import FavouriteElementComponent from "./FavouriteElementComponent";

type Props = {
  currencies: Currency[];
  action: string;
  onButtonPress: Function;
};

const FavouriteListComponent = ({
  currencies,
  action,
  onButtonPress,
}: Props) => {
  return (
    <View>
      <FlatList
        data={currencies}
        renderItem={({ item }) => (
          <FavouriteElementComponent
            currency={item}
            action={action}
            onButtonPress={onButtonPress}
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
