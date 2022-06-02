export interface Reminder {
  id: number;
  name: string;
  description: string;
  frequency: string;
  dateCreated: string;
  timeToRemind: string;
  reminderCategory: string;
  sendType: string;
  status: string;
}