export class UserService {
    universalLink: string = "http://172.20.10.2";
    getUserInfoLink: string = this.universalLink + ":8080/api/v1/user/";

    exampleToken: string =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidGVzdFUiLCJwYXNzd29yZCI6InRlc3QxMjMxMjMxMjMifQ.bHmBljA0uPuObIW2HfF9a4TTV1X_xvo7swSDwL6qywc";

    async getUserInfo() {
        return await fetch(this.getUserInfoLink, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: this.exampleToken,
            },
        })
            .then((response) => response.json())
            .then((data) => {
                return data;
            })
            .catch((error) => {
                console.error(error);
                return error;
            });
    }
}
