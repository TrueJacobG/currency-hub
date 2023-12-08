import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { ScreenNaviagtion } from "./src/type/ScreenNavigation";
import SigninScreen from "./src/screen/SigninScreen";
import LoginScreen from "./src/screen/LoginScreen";
import BaseScreen from "./src/screen/BaseScreen";
import FavouriteScreen from "./src/screen/FavouriteScreen";
import WalletScreen from "./src/screen/WalletScreen";
import AlertScreen from "./src/screen/AlertScreen";
import UserScreen from "./src/screen/UserScreen";

import * as Network from "expo-network";
import { useState } from "react";
import AsyncStorage from "@react-native-async-storage/async-storage";

const Stack = createNativeStackNavigator<ScreenNaviagtion>();

const App = () => {
  const [ipAdress, setIpAdress] = useState(String);

  const getIpAdress = async () => {
    const ip = await Network.getIpAddressAsync();
    console.log(ip);
    AsyncStorage.setItem("ipAdress", ip);
    setIpAdress(ip);
  }

  getIpAdress();

  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Sign" component={SigninScreen} />
        <Stack.Screen name="Log" component={LoginScreen} />
        <Stack.Screen name="Home" component={BaseScreen} />
        <Stack.Screen name="Favourite" component={FavouriteScreen} />
        <Stack.Screen name="Wallet" component={WalletScreen} />
        <Stack.Screen name="Alert" component={AlertScreen} />
        <Stack.Screen name="User" component={UserScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
