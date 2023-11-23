import { FlatList, View } from "react-native";
import { Currency } from "../../type/Currency";
import CurrrencyElementComponent from "./CurrencyElementComponent";

type Props = {
  currencies: Currency[];
};

const CurrencyListComponent = ({ currencies }: Props) => {
  return (
    <View style={{ flex: 1, paddingTop: 20 }}>
      <FlatList data={currencies} renderItem={CurrrencyElementComponent} keyExtractor={(item) => item.currencyCode.toString()} />
    </View>
  );
};

export default CurrencyListComponent;
