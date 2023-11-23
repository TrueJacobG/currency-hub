import { Text, View } from "react-native";
import { Currency } from "../../type/Currency";

type Props = { item: Currency };

const CurrrencyElementComponent = ({ item }: Props) => {
  return (
    <View style={{ padding: 10 }}>
      <Text>{item.currencyCode}</Text>
      <Text>{item.currencyName}</Text>
      <Text>{item.mid}</Text>
    </View>
  );
};

export default CurrrencyElementComponent;
