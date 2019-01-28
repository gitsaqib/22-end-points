# 22 End Points
If you an owner of a micro service and after each deployment (dev, sit, qa, uat, pre-prod, prod, dr), you have to verify all your end point in a single glance. With is this project you can do it in a single click. Yes, You can clone this one.

### Preface
When I joined a SpectrumMobile and I was assign a micro service. As per process everyone have to verify (indeally all end points, even the one you have not touched) their service after deployment. After 2 SIT deployment, I gave up because unfortunately my micro service had twentyyyyy two000 end points. Took a quicker look at google and created my own solution.
It has 4 parts.
* Create data
* Set expected results  
* Run the end points with created data
* Compare output and show RED GREEN

For now I hard coded Made files for "Create Date" and filling the manually and running all end points with created data But sooner or later I will compplete rest.
