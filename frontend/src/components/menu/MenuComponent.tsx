import { StyleSheet, Button, View, Pressable } from "react-native";
import { NativeStackScreenProps } from "@react-navigation/native-stack";
import { ScreenNaviagtion } from "../../type/ScreenNavigation";
import { useNavigation } from '@react-navigation/native'

type Props = NativeStackScreenProps<ScreenNaviagtion, "Menu">;


const MenuComponent = () => {
    const navigation = useNavigation();
    return (
        <View style={styles.container}>
            <Button title="All" onPress={() => navigation.navigate("Fav")}></Button>
            <Button title="Fav" onPress={() => navigation.navigate("Home")}></Button>
            <Button title="Button 3"></Button>
            <Button title="Button 4"></Button>
            <Button title="Button 5"></Button>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flexDirection: "row"
    },
})

export default MenuComponent;
