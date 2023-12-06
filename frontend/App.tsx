import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import BaseScreen from "./src/screen/BaseScreen";
import FavouriteScreen from "./src/screen/FavouriteScreen";
import LoginScreen from "./src/screen/LoginScreen";
import SigninScreen from "./src/screen/SigninScreen";
import { ScreenNaviagtion } from "./src/type/ScreenNavigation";

const Stack = createNativeStackNavigator<ScreenNaviagtion>();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Sign" component={SigninScreen} />
        <Stack.Screen name="Home" component={BaseScreen} />
        <Stack.Screen name="Log" component={LoginScreen} />
        <Stack.Screen name="Favourite" component={FavouriteScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
