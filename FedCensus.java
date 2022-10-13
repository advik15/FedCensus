import java.util.*;
import java.io.*;
import java.util.Comparator;

public class FedCensus
{
	ArrayList<Citizen> citizenList = new ArrayList<Citizen>();
	PriorityQueue<Citizen> pqCit=new PriorityQueue<Citizen>();
	PriorityQueue<Double> pqAge = new PriorityQueue<Double>();
	public FedCensus()
	{
		File filename = new File("FedCensus1930_CambriaCountyPA.txt");
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			String text;
			while(( text=input.readLine())!=null)
			{
				if(text.length()>2 && text.substring(0,2).equals("17"))
				{
					String first=text.substring(71,88).trim();
					String last=text.substring(55,71).trim();
					String streetName=text.substring(20,36).trim();
					String streetNumber=text.substring(36,45).trim();
					String relation=text.substring(88,108).trim();
					String rentOwn=text.substring(108,113).trim();
					String valueProp=text.substring(113,121).trim();
					String gender=text.substring(133,134).trim();
					String age=text.substring(143,151).trim();
					String maritalStatus=text.substring(151,156).trim();
					String ageFirstMarriage=text.substring(156,162).trim();
					String attendSchool=text.substring(162,167).trim();
					String canRead=text.substring(167,173).trim();
					String birthplace=text.substring(173,190).trim();
					String fathersBirthplace=text.substring(190,207).trim();
					String mothersBirthplace=text.substring(207,224).trim();
					String mothertongue=text.substring(224,235).trim();
					String yearImmigrated=text.substring(235,241).trim();
					String occupation=text.substring(252,274).trim();
					String industry=text.substring(274,303).trim();
					String transcibedRemarks=text.substring(342).trim();
					Citizen cit=new Citizen(first,last,streetName,streetNumber,relation,rentOwn,valueProp,gender,age,maritalStatus,ageFirstMarriage,attendSchool,canRead,birthplace,fathersBirthplace,mothersBirthplace,mothertongue,yearImmigrated,occupation,industry,transcibedRemarks);
					citizenList.add(cit);
					pqCit.add(cit);

				}
			}
		}
				catch(IOException e)
								{
									e.printStackTrace();
								}

		for(Citizen c:citizenList)
		{
			pqAge.add(c.getAge());
		}


}
		public void rentMap()
		{
			try
			{
				TreeMap<String,TreeSet<Double>> rentMap = new TreeMap<>();
						for(int x =0;x<citizenList.size();x++)
						{
							String rent = citizenList.get(x).getrentOwn();
							if(!rentMap.containsKey(rent))
								rentMap.put(rent,new TreeSet<Double>());
							rentMap.get(rent).add(citizenList.get(x).getvalueProp());
						}
						Iterator<String> rentiterator = rentMap.keySet().iterator();
						while(rentiterator.hasNext())
						{
							String rentOwn = rentiterator.next();
							System.out.println(rentOwn + ":");
							TreeSet<Double> temp = rentMap.get(rentOwn);
							for(Double num:temp)
								System.out.println("\t$"+num);

						}

			}catch(Exception e){e.printStackTrace();}
		}

		public void myChoice()
		{
			try
			{
				TreeMap<String,HashSet<String>> myChoicemap = new TreeMap<String,HashSet<String>>();
					for(int x = 0;x<citizenList.size();x++)
					{
						String birthplace = citizenList.get(x).getbirthplace();
						if(!myChoicemap.containsKey(birthplace))
							myChoicemap.put(birthplace,new HashSet<String>());
						myChoicemap.get(birthplace).add(citizenList.get(x).getOccupation());
					}
					Iterator<String> iterator = myChoicemap.keySet().iterator();
					while(iterator.hasNext())
					{
						String birth = iterator.next();
						HashSet<String> birthset = myChoicemap.get(birth);
						Iterator<String> occupationset = birthset.iterator();
						System.out.print(birth + " ");
						System.out.print("[");
						while(occupationset.hasNext())
						{
							String temp = occupationset.next();
							System.out.print(temp + ", ");
						}
						System.out.println("]");
					}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		public void genderMap()
		{
			try
			{
				TreeMap<String,HashSet<String>> gendermap = new TreeMap<String,HashSet<String>>();
						for(int x = 0;x<citizenList.size();x++)
						{
							String gender = citizenList.get(x).getGender();
							if(!gendermap.containsKey(gender))
							{
								gendermap.put(gender,new HashSet<String>());
							}
							gendermap.get(gender).add(citizenList.get(x).gettranscibedRemarks());

						}
			//11
					Iterator<String> genderiterator = gendermap.keySet().iterator();
						while(genderiterator.hasNext())
						{
							String gend = genderiterator.next();
							HashSet<String> genderset = gendermap.get(gend);
							Iterator<String> transset = genderset.iterator();
							System.out.println(gend + " ");
							System.out.print("[");
							while(transset.hasNext())
							{
								String temp = transset.next();
								System.out.println(temp + ", ");
							}

							System.out.println("]");
						}

			}catch(Exception e){ e.printStackTrace();}
		}
		public void occupationMap()
		{
			TreeMap<String,HashSet<String>> occupationmap = new TreeMap<String,HashSet<String>>();

					for(int i = 0;i<citizenList.size();i++)
					{
						String occupation = citizenList.get(i).getOccupation();
						if(!occupationmap.containsKey(occupation))
							occupationmap.put(occupation,new HashSet<String>());

						occupationmap.get(occupation).add(citizenList.get(i).getfathersBirthPlace());

					}
					//9
					Iterator<String> occupationiterator = occupationmap.keySet().iterator();
					while(occupationiterator.hasNext())
						{
							String job = occupationiterator.next();
							HashSet<String> occupationset = occupationmap.get(job);
							Iterator<String> birthset = occupationset.iterator();
							System.out.println(job + " ");
							System.out.print("[");
							while(birthset.hasNext())
							{
								String temp = birthset.next();
								System.out.print(temp + ", ");
							}

							System.out.println("]");


						}

		}
		public void mothertongue()
		{
			try{
			TreeMap<String,ArrayList<String>> mothertonguemap = new TreeMap<String,ArrayList<String>>();
		for(int i =0;i<citizenList.size();i++)
		{
			if(!mothertonguemap.containsKey(citizenList.get(i).getmothertongue()))
			{
				mothertonguemap.put(citizenList.get(i).getmothertongue(),new ArrayList<String>());
			}
			mothertonguemap.get(citizenList.get(i).getmothertongue()).add(citizenList.get(i).getFirst() + "," + citizenList.get(i).getLast());

		}
		//7
		Iterator<String> mothertongueiterator = mothertonguemap.keySet().iterator();
		while(mothertongueiterator.hasNext())
		{
			String lang = mothertongueiterator.next();// gets the index
			ArrayList<String> motherlist = mothertonguemap.get(lang); // gets the list inside index
			System.out.print(lang + " "); // prints index
			System.out.print("[");
			System.out.print(motherlist.size()); // prints size of list
			System.out.println("]");


		}

			}catch(Exception e)
			{
				e.printStackTrace();
		}

		}
		public void birthplace()
		{
			try{
				double penn_count = 0.0;
						TreeMap<String,PriorityQueue<Double>> map2 = new TreeMap<String,PriorityQueue<Double>>();
							for(int x = 0;x<citizenList.size();x++)
								{
									if(!map2.containsKey(citizenList.get(x).getbirthplace()))
									{

										map2.put(citizenList.get(x).getbirthplace(),new PriorityQueue<Double>());

									}
										map2.get(citizenList.get(x).getbirthplace()).add(citizenList.get(x).getAge());

								}
						//5
						Iterator<String> it2 = map2.keySet().iterator();
							while(it2.hasNext())
							{
								String birth = it2.next();
								System.out.println(birth + ":");
								PriorityQueue<Double> pq22 = map2.get(birth);
									if(birth.equals("Pennsylvania"))
									{
										System.out.print("[");
										System.out.print(pq22.size());

									}
									else
									{
										System.out.print("[");
										while(!pq22.isEmpty())
										{
											double age = pq22.poll();
											if(age>=0)
											{
												if(pq22.peek()!=null)
													System.out.print(age + " , ");
												else
													System.out.print(age);
											}
									}
								}

								System.out.println("]");

			}
			}catch(Exception e){
					e.printStackTrace();
			}
		}
		public void streetName()
			{
					try
						{
							TreeSet<Citizen> set = new TreeSet<Citizen>();
								for(int i = 0;i<citizenList.size();i++)
								{
									set.add(citizenList.get(i));
								}

							TreeMap<String, TreeSet<Citizen>> map1 = new TreeMap<String, TreeSet<Citizen>>();
								for(int x = 0;x<citizenList.size();x++)
								{
									if(!map1.containsKey(citizenList.get(x).getstreetName()))
										map1.put(citizenList.get(x).getstreetName(),new TreeSet<Citizen>());
									map1.get(citizenList.get(x).getstreetName()).add(citizenList.get(x));

								}

							Iterator<String> it1 = map1.keySet().iterator();
								while(it1.hasNext())
								{
									String street=it1.next();
									System.out.print(street);
									TreeSet<Citizen> sett=map1.get(street);
									Iterator it11=sett.iterator();
									while(it11.hasNext())
									{
										System.out.println("\t"+it11.next());
									}

						}
						}catch(Exception e)
							{
								e.printStackTrace();
							}
		}



	public class Citizen implements Comparable<Citizen>
	{
		private String first;
		private String last;
		private String streetName;
		private int streetNumber;
		private String relation;
		private String rentOwn;
		private Double valueProp;
		private String gender;
		private Double age;
		private String maritalStatus;
		private int ageFirstMarriage;
		private boolean attendSchool;
		private boolean canRead;
		private String birthplace;
		private String fathersBirthPlace;
		private String mothersBirthplace;
		private String mothertongue;
		private int yearImmigrated;
		private String occupation;
		private String industry;
		private String transcibedRemarks;

		public Citizen(String first,String last,String streetName,String streetNumber,String relation,String rentOwn,String valueProp,String gender,String age,
			String maritalStatus,String ageFirstMarriage,String attendSchool,String canRead,String birthplace,String fathersBirthPlace,String mothersBirthplace
			,String mothertongue,String yearImmigrated,String occupation ,String industry ,String transcibedRemarks)
		{
			this.first = first;
			this.last = last;
			this.streetName = streetName;
			try{
				this.streetNumber = Integer.parseInt(streetNumber);
			}catch(NumberFormatException e)
					{
						this.streetNumber=-1;
					}

			this.relation = relation;
			this.rentOwn = rentOwn.substring(0,1);
				if(valueProp.charAt(0)=='$')
				{
					valueProp = valueProp.substring(1);
				}
				try{
					this.valueProp = Double.parseDouble(valueProp);

				}catch(NumberFormatException e)
					{
						if(valueProp.contains("/"))
						{
							String whole = valueProp.substring(0,valueProp.indexOf(" "));
							String numer = valueProp.substring(valueProp.indexOf(" ")+1,valueProp.indexOf("/"));
							String denom = valueProp.substring(valueProp.indexOf("/")+1);
							this.valueProp = Double.parseDouble(whole)+Double.parseDouble(numer)/Double.parseDouble(denom);
						}
						else
						this.valueProp = -1.0;
					}

			this.gender = gender;
			try{
				this.age = Double.parseDouble(age);
			   }catch(NumberFormatException e)
			   {
				 if(age.charAt(0)=='.' || age.equals("un"))
				 	this.age=-1.0;
				 else if(age.charAt(1)==' ' && age.contains("/"))
				 {
					 String whole = age.substring(0,age.indexOf(" "));
					 double dec;
					 if(age.substring(age.indexOf(" ")+1,age.indexOf("/")).contains("*"))
					 	dec = 0.5;
					 else
					 {
						 String numer = age.substring(age.indexOf(" ")+1,age.indexOf("/"));
						 String denom = age.substring(age.indexOf("/")+1);
						 dec = Double.parseDouble(numer)/Double.parseDouble(denom);
					 }
					 this.age = Double.parseDouble(whole)+dec;
				 }
				 	else if(age.contains("*"))
				 	{
						this.age = Double.parseDouble(age.substring(0,age.indexOf("*")));
					}
					else
					{
						String numer = age.substring(0,age.indexOf("/"));
						String denom = age.substring(age.indexOf("/")+1);
						this.age = Double.parseDouble(numer)/Double.parseDouble(denom);
					}
			   }

			this.maritalStatus = maritalStatus;
			try
			{
				this.ageFirstMarriage = Integer.parseInt(ageFirstMarriage);
			}catch(NumberFormatException e)
				{
					this.ageFirstMarriage = -1;
				}

				if(attendSchool.equals("Yes"))
				{
					this.attendSchool = true;
				}
				else
				{
					this.attendSchool = false;
				}

				this.canRead = false;
				if(canRead.equals("Yes"))
				{
					this.canRead = true;
				}

			this.birthplace = birthplace;
			this.fathersBirthPlace = fathersBirthPlace;
			this.mothersBirthplace = mothersBirthplace;
			this.mothertongue = mothertongue;

			try{
				this.yearImmigrated = Integer.parseInt(yearImmigrated);
			}catch(NumberFormatException e)
			{
				this.yearImmigrated=-1;
			}
			this.occupation = occupation.substring(0,1).toUpperCase()+occupation.substring(1).toLowerCase();
			this.industry = industry;
			this.transcibedRemarks = transcibedRemarks;

		}
		public String getFirst()
		{
			return this.first;
		}
		public String getLast()
				{
					return this.last;
		}
		public String getstreetName()
		{
			return this.streetName;
		}
		public int getstreetNumber()
		{
			return this.streetNumber;
		}
		public String getRelation()
		{
			return this.relation;
		}
		public String getrentOwn()
		{
			return this.rentOwn;
		}
		public double getvalueProp()
		{
			return this.valueProp;
		}
		public String getGender()
		{
			return this.gender;
		}
		public double getAge()
		{
			return this.age;
		}
		public String getmaritalStatus()
		{
			return this.maritalStatus;
		}
		public int getageFirstMarriage()
		{
			return this.ageFirstMarriage;
		}public boolean getattendSchool()
		{
			return this.attendSchool;
		}public boolean getcanRead()
		{
			return this.canRead;
		}

		public String getbirthplace()
		{
			return this.birthplace;
		}public String getfathersBirthPlace()
		{
			return this.fathersBirthPlace;
		}
		public String getmothersBirthPlace()
				{
					return this.mothersBirthplace;
		}public String getmothertongue()
		{
			return this.mothertongue;
		}public int getyearImmigrated()
		{
			return this.yearImmigrated;
		}public String getOccupation()
		{
			return this.occupation;
		}
		public String getIndustry()
				{
					return this.industry;
		}
		public String gettranscibedRemarks()
				{
					return this.transcibedRemarks;
		}

public int compareTo(Citizen other)
			{
				return Comparator.comparing(Citizen::getFirst).thenComparing(Citizen::getLast).thenComparing(Citizen::getstreetName).thenComparingInt(Citizen::getstreetNumber).thenComparing(Citizen::getRelation)
				.thenComparing(Citizen::getrentOwn).thenComparingDouble(Citizen::getvalueProp).thenComparing(Citizen::getGender).thenComparingDouble(Citizen::getAge)
				.thenComparing(Citizen::getmaritalStatus).thenComparingInt(Citizen:: getageFirstMarriage).thenComparing(Citizen::getattendSchool).thenComparing(Citizen::getcanRead).thenComparing(Citizen::getbirthplace)
				.thenComparing(Citizen::getfathersBirthPlace).thenComparing(Citizen::getmothersBirthPlace).thenComparing(Citizen::getmothertongue)
				.thenComparingInt(Citizen::getyearImmigrated).thenComparing(Citizen::getOccupation).thenComparing(Citizen::getIndustry).thenComparing(Citizen::gettranscibedRemarks).compare(this,other);

		}
		public String toString()
		{
			return String.format("%-25sAge:%s", last+ ", "+first,age);
		}
	}

	public static void main(String[]args)
	{
		FedCensus cens = new FedCensus();
		cens.streetName();
		cens.birthplace();
		cens.mothertongue();
		cens.occupationMap();
		cens.genderMap();
		cens.rentMap();
		cens.myChoice();
	}


}


