import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import BaseScreen from "./src/screen/BaseScreen";
import LogScreen from "./src/screen/LogScreen";
import SignScreen from "./src/screen/SignScreen";
import { ScreenNaviagtion } from "./src/type/ScreenNavigation";

const Stack = createNativeStackNavigator<ScreenNaviagtion>();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Sign" component={SignScreen} />
        <Stack.Screen name="Home" component={BaseScreen} />
        <Stack.Screen name="Log" component={LogScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
