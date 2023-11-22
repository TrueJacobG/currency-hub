import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { useAtom } from "jotai";
import { StyleSheet, Text, View, FlatList } from "react-native";
import { loggedUserAtom } from "../jotai/loggedUserAtom";
import { ScreenNaviagtion } from "../type/ScreenNavigation";
import { User } from "../type/User";
import React, { useEffect, useState } from "react";

type Props = NativeStackScreenProps<ScreenNaviagtion, "Home">;

const BaseScreen = ({ navigation }: Props) => {
  const [loggedUser, setLoggedUser] = useAtom<User>(loggedUserAtom);

  const [data, setData] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const response = await fetch(
        "http://localhost:8080/api/v1/currency/rate"
      );
      const jsonData = await response.json();
      setData(jsonData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const renderListItem = ({ item }) => (
    <View style={{ padding: 10 }}>
      <Text>{item.currencyCode}</Text>
      <Text>{item.currencyName}</Text>
      <Text>{item.mid}</Text>
    </View>
  );

  return (
    <View>
      <Text style={styles.textintro}>Welcome to CurrencyHub!</Text>
      <Text style={styles.text}>This is not your money any more</Text>
      <View>
        <Text>Email: {loggedUser.email}</Text>
      </View>
      <View style={{ flex: 1, paddingTop: 20 }}>
        <FlatList
          data={data}
          renderItem={renderListItem}
          keyExtractor={(item) => item.currencyCode.toString()}
        />
      </View>
    </View>
  );
};

export default BaseScreen;

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
});
