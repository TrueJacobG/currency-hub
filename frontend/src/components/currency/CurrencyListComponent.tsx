import React from "react";
import { FlatList, View } from "react-native";
import CurrencyElementComponent from "./CurrencyElementComponent";
import { Currency } from "../../type/Currency";

type Props = {
  currencies: Currency[];
  onCurrencyPress: (currency: Currency) => void;
};

const CurrencyListComponent = ({ currencies, onCurrencyPress }: Props) => {
  return (
    <View>
      <FlatList
        data={currencies}
        renderItem={({ item }) => (
          <CurrencyElementComponent item={item} onCloseModal={() => {}} />
        )}
        keyExtractor={(item) => item.currencyCode.toString()}
      />
    </View>
  );
};

export default CurrencyListComponent;