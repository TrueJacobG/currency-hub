import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import BaseScreen from "./src/screen/BaseScreen";
import FavouriteScreen from "./src/screen/FavouriteScreen";
import LoginScreen from "./src/screen/LoginScreen";
import SigninScreen from "./src/screen/SigninScreen";
import { ScreenNaviagtion } from "./src/type/ScreenNavigation";
import WalletScreen from "./src/screen/WalletScreen";
import AlertScreen from "./src/screen/AlertScreen";
import UserScreen from "./src/screen/UserScreen";

const Stack = createNativeStackNavigator<ScreenNaviagtion>();

const App = () => {
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
