import getDatePolish from "@/assets/scripts/dateConvert";
describe('dateConvert unit tests', () => {

    it('initial value is 0', () => {
        let data = new Date("2022-01-01")

        expect(getDatePolish(data)).toBe("sobota, 1 stycznia 2022")
    })


})
