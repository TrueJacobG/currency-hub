import AsyncStorage from "@react-native-async-storage/async-storage";

export class TokenService {
  async getToken() {
    return AsyncStorage.getItem("token").then(async (t) => {
      const val = await t;

      return val;
    });
  }
}
