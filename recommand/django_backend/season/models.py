from django.db import models

from performance.models import Performance


class Season(models.Model):
    # id = models.AutoField(primary_key=True)
    performance = models.ForeignKey(
        Performance, on_delete=models.CASCADE, related_name="performance")
    interpark_id = models.IntegerField(null=True)
    playdb_id = models.IntegerField(unique=True)
    season_image = models.CharField(max_length=255)
    start_date = models.DateTimeField()
    end_date = models.DateTimeField()
    description = models.TextField()
    location = models.CharField(max_length=255)
    runningtime = models.CharField(max_length=255)
    performance_age = models.CharField(max_length=255)
    detail_type = models.CharField(max_length=255)
    proceed_flag = models.SmallIntegerField()
    create_date = models.DateTimeField(auto_now_add=True)

    class Meta:
        managed = False
        db_table = 'season'
