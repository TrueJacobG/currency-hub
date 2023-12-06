export interface RawRateDataItem {
  currencyCode: string;
  currencyName: string;
  currencyTable: string;
  mid: number;
  effectiveDate: string;
}

export const mapRateData = (data: any): RawRateDataItem[] => {
  return data.list.map((item: any) => ({
    currencyCode: item.currencyCode,
    currencyName: item.currencyName,
    currencyTable: item.currencyTable,
    mid: item.mid,
    effectiveDate: item.effectiveDate,
  }));
};
